window.onload = function () {
    var sp = document.getElementById('sp');
    var hamburger = document.getElementById('js-hamburger');
    var blackBg = document.getElementById('js-black-bg');

    hamburger.addEventListener('click', function () {
        sp.classList.toggle('open');
        hamburger.classList.toggle('open');
    });
    blackBg.addEventListener('click', function () {
        sp.classList.remove('open');
    });
};
