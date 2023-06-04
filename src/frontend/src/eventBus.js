import mitt from 'mitt'

const emitter = mitt()

export function emit (event, ...args) {
  emitter.emit(event, ...args)
}

export function on (event, handler) {
  emitter.on(event, handler)
}

export function off (event, handler) {
  emitter.off(event, handler)
}