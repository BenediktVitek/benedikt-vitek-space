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

    if (firstInput === secondInput) {
        return;
    }

    document.getElementById('registerPassword').value = '';
    document.getElementById('passwordCheck').value = '';

    event.preventDefault();
    alert("Password mismatch");

}

function scrollToTarget(targetId) {
    const targetElement = document.getElementById(targetId);
    if (targetElement) {
        targetElement.scrollIntoView({behavior: 'smooth'});
    }
}

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('about-me-btn').addEventListener('click', function (e) {
        e.preventDefault();
        scrollToTarget('about-me-area');
    });
});

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('about-site-btn').addEventListener('click', function (e) {
        e.preventDefault();
        scrollToTarget('about-site-area');
    });
});

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('my-skills-btn').addEventListener('click', function (e) {
        e.preventDefault();
        scrollToTarget('my-skills-area');
    });
});


function showCategory(category) {
    const rows = document.querySelectorAll('#skills-table tr:not(:first-child)');
    rows.forEach(row => row.style.display = 'none');

    // Show rows with the selected category
    const categoryRows = document.querySelectorAll(`.${category}`);
    categoryRows.forEach(row => row.style.display = '')
}

