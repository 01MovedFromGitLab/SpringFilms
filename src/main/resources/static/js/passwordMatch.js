
function checkPass() {
    //Store the password field objects into variables ...
    var password = document.getElementById('password2');
    var confirm = document.getElementById('confirm2');
    //Store the Confirmation Message Object ...
    var message = document.getElementById('confirm-message2');
    //Set the colors we will be using ...
    var good_color = "#66cc66";
    var bad_color = "#ff6666";
    //Compare the values in the password field
    //and the confirmation field
    if (password.value == confirm.value) {
        //The passwords match.
        //Set the color to the good color and inform
        //the user that they have entered the correct password
        confirm.style.backgroundColor = good_color;
        message.style.color = good_color;
        message.innerHTML = '<img src="/wp-content/uploads/2019/04/tick.png" alt="Passwords Match!">';
    } else {
        //The passwords do not match.
        //Set the color to the bad color and
        //notify the user.
        confirm.style.backgroundColor = bad_color;
        message.style.color = bad_color;
        message.innerHTML = '<img src="/wp-content/uploads/2019/04/publish_x.png" alt="Passwords Do Not Match!">';
    }
}

