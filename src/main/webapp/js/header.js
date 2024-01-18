import { HEADER_INFO } from "./const.js";

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
const addId = (el, id) => el.setAttribute("id", id);

const currentUrl = window.location.href;
const currentUrlArray = currentUrl.split("/");
window.addEventListener("DOMContentLoaded", async () => {
	const defaultHeaderEl = gebi("header");
	const spMenuEl = gebi("sp") ;
	console.log("default: ", defaultHeaderEl);
	console.log("spMenuEl: ", spMenuEl);
	
	createDefaultHeader(defaultHeaderEl, spMenuEl);
	createSpMenu(spMenuEl);
	
	
    
})

const createDefaultHeader = (parentEl, spMenuEl) => {
	const headerContainerEl = ce("div");
	addClasses(headerContainerEl, ["header-container"]);
	createLogo(headerContainerEl);
	createNav(false, headerContainerEl);
	createHamburgerMenu(headerContainerEl, spMenuEl);
	ac(headerContainerEl, parentEl);
}

const createSpMenu = (parentEl) => {
	createNav(true, parentEl);
	const blackBgEl = ce("div");
	addClasses(blackBgEl, ["black-bg"]);
	addId(blackBgEl, "js-black-bg");
	blackBgEl.addEventListener('click', () => {
        parentEl.classList.remove('open');
    });
	ac(blackBgEl, parentEl);
}


const createLogo = (parentEl) => {
	const logoLinkEl = ce("a");
	logoLinkEl.setAttribute("href", "/hotmot/ProductListServlet");
	addClasses(logoLinkEl, ["logo"]);
	const logoEl = ce("h1");
	const logoImageEl = ce("img");
	logoImageEl.setAttribute("src", "./images/logo.png");
	ac(logoImageEl, logoEl);
	ac(logoEl, logoLinkEl);
	ac(logoLinkEl, parentEl)
}

const createNav = (isSp, parentEl) => {
	const navEl = ce("nav");
	addClasses(navEl, ["nav"]);
	const menuEl = ce("ul");
	addClasses(navEl, ["menu"]);
	
	HEADER_INFO.forEach(x => {
		const menuItemEl = ce("li");
		const menuItemLinkEl = ce("a");
		menuItemLinkEl.setAttribute("href", `/hotmot/${x.servletName}`);
		console.log("currentUrlArray: ", currentUrlArray);
		console.log("servlet: ", x.servletName)
		if(currentUrlArray.includes(x.servletName)) {
			addClasses(menuItemLinkEl, ["current"]);
		}
		const menuItemIconEl = ce("i");
		const iconClasses = ["fa-solid", x.iconClass];
		if(isSp) {
			iconClasses.push("fa-lg");
		}
		addClasses(menuItemIconEl, iconClasses);
		const menuItemLabelEl = ce("span");
		menuItemLabelEl.innerText = x.label;
		ac(menuItemIconEl, menuItemLinkEl);
		ac(menuItemLabelEl, menuItemLinkEl);
		ac(menuItemLinkEl, menuItemEl);
		ac(menuItemEl, menuEl);
		ac(menuEl, navEl);
		ac(navEl, parentEl);
	})
}

const createHamburgerMenu = (parentEl, spMenuEl) => {
	const hamburgerEl = ce("div");
	addClasses(hamburgerEl, ["hamburger"]);
	addId(hamburgerEl, "js-hamburger");
	for(let i = 1; i <= 3; i++) {
		const hamburgerChildEl = ce("span");
		addClasses(hamburgerChildEl, ["hamburger__line", `hamburger__line--${i}`]);
		ac(hamburgerChildEl, hamburgerEl);
	}
	hamburgerEl.addEventListener('click',  () => {
        spMenuEl.classList.toggle('open');
    });
	ac(hamburgerEl, parentEl);
}




