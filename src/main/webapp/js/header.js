import {
  ce,
  gebi,
  ac,
  addClasses,
  setHref,
  setSrc,
  removeClass,
} from "../js/utils.js";

const addId = (el, id) => el.setAttribute("id", id);

const userIdEl = gebi("userId");
const cartIdEl = gebi("cartId");

const HEADER_INFO = [
  {
    label: "ホーム",
    servletName: "ProductListServlet",
    iconClass: "fa-house",
  },
  {
    label: "ブックマーク",
    servletName: `BookMarkServlet?userId=${userIdEl.value}`,
    iconClass: "fa-star",
  },
  {
    label: "カート",
    servletName: `CartDetailListServlet?cartId=${cartIdEl.value}`,
    iconClass: "fa-cart-shopping",
  },
  {
    label: "注文履歴",
    servletName: `OrderHistoryServlet?usreId=${userIdEl.value}`,
    iconClass: "fa-clock-rotate-left",
  },
  {
    label: "ログアウト",
    servletName: "LogoutServlet",
    iconClass: "fa-right-from-bracket",
  },
];

const currentUrl = window.location.href;
const currentUrlArray = currentUrl.split("/");
window.addEventListener("DOMContentLoaded", async () => {
  const defaultHeaderEl = gebi("header");
  const bodyEl = document.body;
  bodyEl.style.paddingTop = defaultHeaderEl.clientHeight + 20 + "px";
  const spMenuEl = gebi("sp");

  createDefaultHeader(defaultHeaderEl, spMenuEl);
  createSpMenu(spMenuEl);
});

const createDefaultHeader = (parentEl, spMenuEl) => {
  const headerContainerEl = ce("div");
  addClasses(headerContainerEl, ["header-container"]);
  createLogo(headerContainerEl);
  createNav(false, headerContainerEl);
  createHamburgerMenu(headerContainerEl, spMenuEl);
  ac(headerContainerEl, parentEl);
};

const createSpMenu = (parentEl) => {
  createNav(true, parentEl);
  const blackBgEl = ce("div");
  addClasses(blackBgEl, ["black-bg"]);
  addId(blackBgEl, "js-black-bg");
  blackBgEl.addEventListener("click", () => {
    parentEl.classList.remove("open");
  });
  ac(blackBgEl, parentEl);
};

const createLogo = (parentEl) => {
  const logoLinkEl = ce("a");
  logoLinkEl.setAttribute("href", "/hotmot/ProductListServlet");
  addClasses(logoLinkEl, ["logo"]);
  const logoEl = ce("h1");
  const logoImageEl = ce("img");
  logoImageEl.setAttribute("src", "./images/logo.png");
  ac(logoImageEl, logoEl);
  ac(logoEl, logoLinkEl);
  ac(logoLinkEl, parentEl);
};

const createNav = (isSp, parentEl) => {
  const navEl = ce("nav");
  addClasses(navEl, ["nav"]);
  const menuEl = ce("ul");
  addClasses(navEl, ["menu"]);

  HEADER_INFO.forEach((x) => {
    const menuItemEl = ce("li");
    const menuItemLinkEl = ce("a");
    menuItemLinkEl.setAttribute("href", `/hotmot/${x.servletName}`);
    if (currentUrlArray.includes(x.servletName)) {
      addClasses(menuItemLinkEl, ["current"]);
    }
    const menuItemIconEl = ce("i");
    const iconClasses = ["fa-solid", x.iconClass];
    if (isSp) {
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
  });
};

const createHamburgerMenu = (parentEl, spMenuEl) => {
  const hamburgerEl = ce("div");
  addClasses(hamburgerEl, ["hamburger"]);
  addId(hamburgerEl, "js-hamburger");
  for (let i = 1; i <= 3; i++) {
    const hamburgerChildEl = ce("span");
    addClasses(hamburgerChildEl, ["hamburger__line", `hamburger__line--${i}`]);
    ac(hamburgerChildEl, hamburgerEl);
  }
  hamburgerEl.addEventListener("click", () => {
    spMenuEl.classList.toggle("open");
  });
  ac(hamburgerEl, parentEl);
};
