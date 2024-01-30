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

const formEl = gebi("form");
const inputEmailEl = gebi("email");
const inputPasswordEl = gebi("password");

formEl.addEventListener("submit", async (e) => {
  e.preventDefault();
  console.log("submit", e.target);
  const email = inputEmailEl.value;
  const password = inputPasswordEl.value;

  await fetch("/hotmot/LoginServlet", {
    method: "POST",
    body: JSON.stringify({ email, password }),
  })
    .then((response) => {
      console.log("res: ", response);
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((data) => {
      console.log("Data from Servlet:", data);
    })
    .catch((err) => console.log("err: ", err));
});
