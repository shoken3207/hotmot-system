const ce = (el) => document.createElement(el);
const ctn = (el) => document.createTextNode(el);
const gebi = (id) => document.getElementById(id);
const qs = (query) => document.querySelector(query);
const ac = (child, parent) => parent.appendChild(child);
const rlc = (parentEl) => parentEl.removeChild(parentEl.lastElementChild);
const addClasses = (el, classNames) => {
  classNames.forEach((className) => {
    el.classList.add(className);
  });
};
const removeClass = (el, className) => el.classList.remove(className);
const setId = (el, id) => el.setAttribute('id', id);
const setHref = (el, href) => el.setAttribute('href', href);
const setSrc = (el, src) => el.setAttribute('src', src);
const setValue = (el, value) => el.setAttribute('value', value);

const showToast = ({ text = '', destination = '' }) => {
  Toastify({
    text,
    duration: 3000,
    destination,
    //      newWindow: true,
    close: true,
    gravity: 'bottom',
    position: 'right',
    stopOnFocus: true,
    style: {
      background: 'linear-gradient(to right, #00b09b, #96c93d)',
    },
    //      onClick: function () {},
  }).showToast();
};

export {
  ce,
  ctn,
  gebi,
  qs,
  ac,
  rlc,
  addClasses,
  removeClass,
  setId,
  setHref,
  setSrc,
  setValue,
  showToast,
};
