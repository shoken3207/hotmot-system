import {
  gebi,
  showToast,
} from "../js/utils.js";

const messageEl = gebi("message");

window.addEventListener("DOMContentLoaded", () => {
	const message = messageEl.value;
	console.log(message)
	if(message !== "null") {
		showToast({text: message});
	}
})
