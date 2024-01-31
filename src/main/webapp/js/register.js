const ce = (el) => document.createElement(el);
const ctn = (el) => document.createTextNode(el);
const gebi = (id) => document.getElementById(id);
const qs = (query) => document.querySelector(query);
const ac = (child, parent) => parent.appendChild(child);
const rlc = (parentEl) => parentEl.removeChild(parentEl.lastElementChild) 
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


const formEl = gebi("form");
const inputEmailEl = gebi("email");
const inputPasswordEl = gebi("password");
const inputConfirmPasswordEl = gebi("confirmPassword");

formEl.addEventListener("submit", async (e) => {
  e.preventDefault();
  console.log("submit", e.target);
  const email = inputEmailEl.value;
  const password = inputPasswordEl.value;
  const confirmPassword = inputConfirmPasswordEl.value;
  
  if(password !== confirmPassword) {
    console.log("パスワードと確認用パスワードが異なります。")
    return;
  }
  
  console.log(email, password, confirmPassword)
  
  await fetch("/hotmot/LoginServlet", {
    method: "POST",
    body: JSON.stringify({email, password}),
  }).then(response => {
			console.log("res: ", response)
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Data from Servlet:', data);
        }).catch((err) => console.log("err: ", err));
})