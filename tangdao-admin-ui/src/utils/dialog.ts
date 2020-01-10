function ts(t:any){
    document.body.appendChild(t);
}
export default {
    bind(el: any, binding: any, vnode: any) {


        
        const modalId = binding.arg || 'modalId';
        console.log(modalId);
        //modal-backdrop fade show
        // const $modalBackdrop = document.createElement('div');
        // $modalBackdrop.setAttribute('class', 'modal-backdrop fade show');
        document.body.setAttribute('class','modal-open');
    //     $popper.setAttribute('role', 'tooltip');

    //     // const $content = document.createElement('div');
    //     // $content.setAttribute('class', 'tooltip-content');

    //     const $arrow = document.createElement('div');
    //     $arrow.setAttribute('class', 'arrow');
    //     $arrow.setAttribute('x-arrow', '');
    //     // if(placement=='right'){

    //     //     $arrow.setAttribute('style', 'top: 8px');
    //     // }
    //     // $content.appendChild($arrow);

    //     const $inner = document.createElement('div');
    //     $inner.setAttribute('class', 'tooltip-inner');


    //     // $content.appendChild($inner);
    //     $popper.appendChild($arrow);
    //     $popper.appendChild($inner);
    //     if (binding.value) {
    //       if (binding.value.appendToBody === false) {
    //         $popper.style.display = 'none';
    //         el.appendChild($popper);
    //       } else {
    //         $popper.className += ` fade  ${binding.value.popperCls ? binding.value.popperCls.join(' ') : ''} show`;
    //       }
    //     }

    //     const options = Object.assign({}, binding.value, { placement });

    //     el.$inner = $inner;
    //     el.popper = new Popper(el, $popper, options);
    //     setProperties(el, binding);
    //     setAttributes($inner, el);
      },
    //   inserted(el: any, binding: any, vnode: any, oldVnode: any) {
    //     addEvent(el);
    //   },
    //   unbind(el: any, binding: any, vnode: any, oldVnode: any) {
    //     removeEvent(el);
    //     el.popper.destroy();
    //     if (binding.value.appendToBody === false) {
    //       if (el.popper.popper) { el.removeChild(el.popper.popper); }
    //     } else if (el.popper.popper && el.popper.popper.parentNode === document.body) {
    //       document.body.removeChild(el.popper.popper);
    //     }
    //   },
    //   componentUpdated(el: any, binding: any, vnode: any, oldVnode: any) {
    //     setProperties(el, binding);
    //     setAttributes(el.$inner, el);
    //   },
}