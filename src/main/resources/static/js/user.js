let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });
        $("#btn-update").on("click", () => {
            this.update();
        });
    },

    save: function () {

        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

        if (checkUsername && validatePassword() && validateEmail()) {
            $.ajax({
                type: "POST",
                url: "/auth/joinProc",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8"
            }).done(function (resp) {
                if (resp.status === 500) {
                    alert("Failed to Sign Up");
                } else {
                    alert("Success Sign Up");
                    location.href = "/auth/loginForm";
                }
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        } else {
            alert("Please Check ID and Password or Email");
        }
    },
   update: function () {
         let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
       if(validatePassword() && validateEmail()) {
          $.ajax({
             type: "PUT",
              url: "/user",
               data: JSON.stringify(data),
               contentType: "application/json; charset=utf-8",
               }).done(function (resp) {
                alert("Success Update User Info");

                }).fail(function (error) {
                  alert(JSON.stringify(error));
                   console.error('Error:', error);
               });
               } else {
                   alert("Please Check Password and email");
                   }
    }
}

 let checkUsername;
 function validateUsername () {
    const $resultUsername = $('#resultUsername');
    const usernameValue = $('#username').val();
    $resultUsername.text('');

    let checkedLength = usernameValue.length >= 4 ? true : false;
    let checkDuplication = false;
     $.ajax({
        type: "GET",
        url: "/auth/username/" +usernameValue,
        async: false,
        contentType: "application/json; charset=utf-8",
    }).done(function (resp) {
        checkDuplication = resp.data;
//    });
}).fail(function (error) {
        console.error('Error checking username:', error);
        $resultUsername.text('Error validating username');
        $resultUsername.css('color', 'red');
    });

    checkUsername = checkDuplication && checkedLength;
    if (checkUsername) {
        $resultUsername.text('Valid Username');
        $resultUsername.css('color', 'green');
    } else {
        $resultUsername.text('Invalid Username');
        $resultUsername.css('color', 'red');
    }
}


const validateInputPassword = (password) => {
    return password.match(
        /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/
    );
};

const validatePassword = () => {
    const $resultPassword = $('#resultPassword');
    const password = $('#password').val();
    $resultPassword.text('');

// Line 91 - 100
    if (validateInputPassword(password)) {
        $resultPassword.text('Valid Password');
        $resultPassword.css('color', 'green');
        return true;
    } else {
        $resultPassword.text('Invalid Password: One Upper Character, and One Special Character, and Length is 8 to 15');
        $resultPassword.css('color', 'red');
        }  return false;
  }


// Line 101 - 110
const validateInputEmail = (email) => {
    return email.match(
        /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    );
};

const validateEmail = () => {
    const $resultEmail = $('#resultEmail');
    const email = $('#email').val();
    $resultEmail.text('');


    if (validateInputEmail(email)) {
        $resultEmail.text('Valid Email Address');
        $resultEmail.css('color', 'green');
        return true;
    } else {
        $resultEmail.text('Invalid Email: Format(test@test.com)');
        $resultEmail.css('color', 'red');
   }       return false;

}


index.init();

$('#username').focusout(validateUsername);
$('#password').on('input', validatePassword);
$('#email').on('input', validateEmail);
