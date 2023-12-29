function submitByAuthorForm() {

    event.preventDefault();

    var commentAuthorValue = document.getElementById('commentAuthor').value;

    if (commentAuthorValue.trim() === "") {
        alert("Please enter a value for the author.");
        return false;
    }

    document.getElementById('oneAuthorForm').action = 'api/comments/users/' + commentAuthorValue;

    document.getElementById('oneAuthorForm').submit();

    document.getElementById('commentAuthor').value = '';

}

function submitByIdForm() {
    event.preventDefault();

    var commentIdValue = document.getElementById('commentId').value;

    var isValidInput = /^[1-9]\d*$/.test(commentIdValue)

    if (!isValidInput) {
        alert("Please enter valid ID (whole positive number higher than 0).");
        return false;
    }

    document.getElementById('byIdForm').action = 'api/comments/' + commentIdValue;

    document.getElementById('byIdForm').submit();

    document.getElementById('commentId').value = '';

}

function checkFields() {
    var firstInput = document.getElementById('registerPassword').value;
    var secondInput = document.getElementById('passwordCheck').value;

    if(firstInput === secondInput) {
        return;
    }

    document.getElementById('registerPassword').value = '';
    document.getElementById('passwordCheck').value = '';

    event.preventDefault();
    alert("Password mismatch");

}
