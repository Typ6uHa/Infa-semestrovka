function check(){


    var nickname = document.getElementById('nickname').value;
    var name = document.getElementById('name').value;
    var surname = document.getElementById('surname').value;
    var city = document.getElementById('city').value;
    var login = document.getElementById('login').value;
    var reg_password = document.getElementById('password').value;
    var reg_password2 = document.getElementById('password2').value;

    if(login.length === 0 || nickname.length === 0 || reg_password.length === 0 || reg_password2.length === 0
    || name.length === 0 || surname === 0 || city === 0){
        $("#err").append('Все поля должны быть заполнены!');
        return false;
    }

    if(/^[a-zA-Z1-9]+$/.test(login) === false) {
        $("#err").append('В логине должны быть только латинские буквы');
        return false;
    }
    if(login.length < 4 || login.length > 16) {
        $("#err").append('В логине должно быть от 4 до 16 символов');
        return false;
    }

    if(/^[a-zA-Z1-9]+$/.test(nickname) === false) {
        $("#err").append('В никнейме должны быть только латинские буквы');
        return false;
    }
    if(nickname.length < 4 || nickname.length > 16) {
        $("#err").append('В никнейме должно быть от 4 до 16 символов');
        return false;
    }

    if (reg_password !== reg_password2){
        $("#err").append('Повторный пароль введен неверно!');
        return false;
    }

    if(/(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{4, 16}$/.test(reg_password) === false) {
        $("#err").append('Пароль должен содержать одну строчную, одну заглавную буквы, цифру ' +
            'и быть длиной не менее 4 символов и не более 16');
        return false;
    }
    return true;
}
