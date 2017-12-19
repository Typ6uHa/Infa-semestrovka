function checkLogin(){
    var login = document.getElementById('login').value;
    var password = document.getElementById('password').value;

    if(login.length === 0 || password.length === 0){
        $("#err").append('Все поля должны быть заполнены!');
        return false;
    }

    return true;
}
