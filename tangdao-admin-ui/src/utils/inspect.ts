import { isArray } from './array'
import { isObject, isPlainObject } from './object'

// --- Convenience inspection utilities ---

export const toType = (val: any) => typeof val

export const toRawType = (val: any) => Object.prototype.toString.call(val).slice(8, -1)

export const toRawTypeLC = (val: any) => toRawType(val).toLowerCase()

export const isUndefined = (val: any) => val === undefined

export const isNull = (val: any) => val === null

export const isEmptyString = (val: any) => val === ''

export const isUndefinedOrNull = (val: any) => isUndefined(val) || isNull(val)

export const isUndefinedOrNullOrEmpty = (val: any) => isUndefinedOrNull(val) || isEmptyString(val)

export const isFunction = (val: any) => toType(val) === 'function'

export const isBoolean = (val: any) => toType(val) === 'boolean'

export const isString = (val: any) => toType(val) === 'string'

export const isNumber = (val: any) => toType(val) === 'number'

export const isPrimitive = (val: any) => isBoolean(val) || isString(val) || isNumber(val)

export const isDate = (val: any) => val instanceof Date

export const isEvent = (val: any) => val instanceof Event

export const isRegExp = (val: any) => toRawType(val) === 'RegExp'

export const isPromise = (val: any) =>
    !isUndefinedOrNull(val) && isFunction(val.then) && isFunction(val.catch)

// Extra convenience named re-exports
export { isArray, isObject, isPlainObject }