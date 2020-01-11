// --- Static ---

export const from = Array.from
export const isArray = Array.isArray

// --- Instance ---

export const arrayIncludes = (array: any, value: any) => array.indexOf(value) !== -1
export const concat = (...args: any) => Array.prototype.concat.apply([], args)