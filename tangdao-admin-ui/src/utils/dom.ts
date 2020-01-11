import { from as arrayFrom } from './array'
import { isFunction, isNull, isObject } from '../utils/inspect'

// Normalize event options based on support of passive option
// Exported only for testing purposes
export const parseEventOptions = (options: any) => {
    /* istanbul ignore else: can't test in JSDOM, as it supports passive */
    // if (hasPassiveEventSupport) {
    //     return isObject(options) ? options : { useCapture: !!options || false }
    // } else {
    // Need to translate to actual Boolean value
    return !!(isObject(options) ? options.useCapture : options)
    // }
}
// Attach an event listener to an element
export const eventOn = (el: any, evtName: any, handler: any, options: any) => {
    if (el && el.addEventListener) {
        el.addEventListener(evtName, handler, parseEventOptions(options))
    }
}

// Remove an event listener from an element
export const eventOff = (el: any, evtName: any, handler: any, options: any) => {
    if (el && el.removeEventListener) {
        el.removeEventListener(evtName, handler, parseEventOptions(options))
    }
}

// Remove a node from DOM
export const removeNode = (el: any) => el && el.parentNode && el.parentNode.removeChild(el)

// Determine if an element is an HTML element
export const isElement = (el: any) => Boolean(el && el.nodeType === Node.ELEMENT_NODE)

// Determine if an element is disabled
export const isDisabled = (el: any) =>
    !isElement(el) || el.disabled || hasAttr(el, 'disabled') || hasClass(el, 'disabled')

// Cause/wait-for an element to reflow it's content (adjusting it's height/width)
export const reflow = (el: any) => {
    // Requesting an elements offsetHight will trigger a reflow of the element content
    /* istanbul ignore next: reflow doesn't happen in JSDOM */
    return isElement(el) && el.offsetHeight
}

// Select all elements matching selector. Returns `[]` if none found
export const selectAll = (selector: any, root: any) =>
    arrayFrom((isElement(root) ? root : document).querySelectorAll(selector))

// Select a single element, returns `null` if not found
export const select = (selector: any, root: any) =>
    (isElement(root) ? root : document).querySelector(selector) || null

// Returns true if the parent element contains the child element
export const contains = (parent: any, child: any) => {
    if (!parent || !isFunction(parent.contains)) {
        return false
    }
    return parent.contains(child)
}

// Get an element given an ID
export const getById = (id: string) => document.getElementById(/^#/.test(id) ? id.slice(1) : id) || null

// Add a class to an element
export const addClass = (el: any, className: string) => {
    // We are checking for `el.classList` existence here since IE 11
    // returns `undefined` for some elements (e.g. SVG elements)
    // See https://github.com/bootstrap-vue/bootstrap-vue/issues/2713
    if (className && isElement(el) && el.classList) {
        el.classList.add(className)
    }
}

// Remove a class from an element
export const removeClass = (el: any, className: string) => {
    // We are checking for `el.classList` existence here since IE 11
    // returns `undefined` for some elements (e.g. SVG elements)
    // See https://github.com/bootstrap-vue/bootstrap-vue/issues/2713
    if (className && isElement(el) && el.classList) {
        el.classList.remove(className)
    }
}

// Test if an element has a class
export const hasClass = (el: any, className: string) => {
    // We are checking for `el.classList` existence here since IE 11
    // returns `undefined` for some elements (e.g. SVG elements)
    // See https://github.com/bootstrap-vue/bootstrap-vue/issues/2713
    if (className && isElement(el) && el.classList) {
        return el.classList.contains(className)
    }
    return false
}

// Set an attribute on an element
export const setAttr = (el: any, attr: any, val: any) => {
    if (attr && isElement(el)) {
        el.setAttribute(attr, val)
    }
}

// Remove an attribute from an element
export const removeAttr = (el: any, attr: any) => {
    if (attr && isElement(el)) {
        el.removeAttribute(attr)
    }
}

// Get an attribute value from an element
// Returns `null` if not found
export const getAttr = (el: any, attr: any) => (attr && isElement(el) ? el.getAttribute(attr) : null)

// Determine if an attribute exists on an element
// Returns `true` or `false`, or `null` if element not found
export const hasAttr = (el: any, attr: any) => (attr && isElement(el) ? el.hasAttribute(attr) : null)

// Return the Bounding Client Rect of an element
// Returns `null` if not an element
/* istanbul ignore next: getBoundingClientRect() doesn't work in JSDOM */
export const getBCR = (el: any) => (isElement(el) ? el.getBoundingClientRect() : null)