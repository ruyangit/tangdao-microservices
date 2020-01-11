import KeyCodes from './key-codes'
import {
    eventOn,
    eventOff,
    getAttr,
    hasAttr,
    isDisabled,
    select,
    setAttr
} from './dom'
import { isString } from './inspect'
import { keys } from './object'

const EVENT_SHOW = 'td::show::modal'

const HANDLER = '__td_modal_directive__'

const EVENT_OPTS = { passive: true }

const getTarget = (binding: any) => {
    const { modifiers = {}, arg, value } = binding;
    // Try value, then arg, otherwise pick last modifier
    return isString(value) ? value : isString(arg) ? arg : keys(modifiers).reverse()[0]
}

const getTriggerElement = (el: any) => {
    // If root element is a dropdown-item or nav-item, we
    // need to target the inner link or button instead
    return el ? select('a, button', el) || el : el
}

const setRole = (trigger: any) => {
    // Ensure accessibility on non button elements
    if (trigger && trigger.tagName !== 'BUTTON') {
        // Only set a role if the trigger element doesn't have one
        if (!hasAttr(trigger, 'role')) {
            setAttr(trigger, 'role', 'button')
        }
        // Add a tabindex is not a button or link, and tabindex is not provided
        if (trigger.tagName !== 'A' && !hasAttr(trigger, 'tabindex')) {
            setAttr(trigger, 'tabindex', '0')
        }
    }
}

const bind = (el: any, binding: any, vnode: any) => {
    const target = getTarget(binding)
    const trigger = getTriggerElement(el)
    if (target && trigger) {
        const handler = (evt: any) => {
            // `currentTarget` is the element with the listener on it
            const currentTarget = evt.currentTarget
            if (!isDisabled(currentTarget)) {
                const type = evt.type
                const key = evt.keyCode
                // Open modal only if trigger is not disabled
                if (
                    type === 'click' ||
                    (type === 'keydown' && (key === KeyCodes.ENTER || key === KeyCodes.SPACE))
                ) {
                    vnode.context.$root.$emit(EVENT_SHOW, target, currentTarget)
                }
            }
        }
        el[HANDLER] = handler
        // If element is not a button, we add `role="button"` for accessibility
        setRole(trigger)
        // Listen for click events
        eventOn(trigger, 'click', handler, EVENT_OPTS);
        if (trigger.tagName !== 'BUTTON' && getAttr(trigger, 'role') === 'button') {
            // If trigger isn't a button but has role button,
            // we also listen for `keydown.space` && `keydown.enter`
            eventOn(trigger, 'keydown', handler, EVENT_OPTS)
        }
    }
}

const unbind = (el: any, binding: any, vnode: any, oldVnode: any) => {

    const trigger = getTriggerElement(el)
    const handler = el ? el[HANDLER] : null
    if (trigger && handler) {
        eventOff(trigger, 'click', handler, EVENT_OPTS)
        eventOff(trigger, 'keydown', handler, EVENT_OPTS)
    }
    delete el[HANDLER]
}
const componentUpdated = (el: any, binding: any, vnode: any, oldVnode: any) => {
    unbind(el, binding, vnode, oldVnode)
    bind(el, binding, vnode)
}

const updated = () => { }

export default {
    inserted: componentUpdated,
    updated,
    componentUpdated,
    unbind
}