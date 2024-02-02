import {
  ce,
  gebi,
  ac,
  addClasses,
  setHref,
  setSrc,
  setValue,
  removeClass,
  rlc,
  setId,
  showToast,
} from "../js/utils.js";

 
 window.addEventListener("DOMContentLoaded", async () => {
	
	const topScrollButtonEl = ce("a");
	setHref(topScrollButtonEl, "#")
	addClasses(topScrollButtonEl, ["top-scroll-button"]);
	const arrowIconEl = ce("i");
	arrowIconEl.style="color: white";
	addClasses(arrowIconEl, ["fa-solid", "fa-chevron-up", "fa-2x"])
	ac(arrowIconEl, topScrollButtonEl);
	ac(topScrollButtonEl, document.body);
	window.addEventListener("scroll", () => {
		if(window.scrollY > 300) {
			if(!topScrollButtonEl.classList.contains('disp')) {
				addClasses(topScrollButtonEl, ["disp"])
			}
		}else {
			if(topScrollButtonEl.classList.contains('disp')) {
				removeClass(topScrollButtonEl, "disp")
			}
		}
	})
})