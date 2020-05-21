window.onload = init;

function init() {
    var registerBtn = document.getElementById("register-btn");
    if (registerBtn != null) {
        registerBtn.onclick = register;
    }
}

function register() {
    var userForm = document.getElementById("user-form");
    userForm.setAttribute("action", "register");
    userForm.submit();
}