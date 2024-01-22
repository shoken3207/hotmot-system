
const ce = (el) => document.createElement(el);
const ctn = (el) => document.createTextNode(el);
const gebi = (id) => document.getElementById(id);
const qs = (query) => document.querySelector(query);
const ac = (child, parent) => parent.appendChild(child);
const addClasses = (el, classNames) =>{
	classNames.forEach(className => {
		el.classList.add(className);
	})
};
const removeClass = (el, className) => el.classList.remove(className);
const setId = (el, id) => el.setAttribute("id", id);
const setHref = (el, href) => el.setAttribute("href", href);
const setSrc = (el, src) => el.setAttribute("src", src);
const setValue = (el, value) => el.setAttribute("value", value);



export {ce, ctn, gebi, qs, ac, addClasses, removeClass, setId, setHref, setSrc, setValue}