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

document.addEventListener('DOMContentLoaded', function() {
    var loginFailedMessage = document.getElementById('login-failed');

    if (loginFailedMessage) {
        loginFailedMessage.scrollIntoView({ behavior: 'smooth' });
    }
});

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('about-me-btn').addEventListener('click', function (e) {
        e.preventDefault();
        scrollToTarget('about-me-area');
    });
    document.getElementById('header-about-me-button').addEventListener('click', function (e) {
        e.preventDefault();
        scrollToTarget('about-me-area');
    });
});

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('about-site-btn').addEventListener('click', function (e) {
        e.preventDefault();
        scrollToTarget('about-site-area');
    });
    document.getElementById('about-site-btn-two').addEventListener('click', function (e) {
        e.preventDefault();
        scrollToTarget('about-site-area');
    });
    document.getElementById('header-about-site-button').addEventListener('click', function (e) {
        e.preventDefault();
        scrollToTarget('about-site-area');
    });
});

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('my-skills-btn').addEventListener('click', function (e) {
        e.preventDefault();
        scrollToTarget('my-skills-area');
    });
    document.getElementById('header-skills-button').addEventListener('click', function (e) {
        e.preventDefault();
        scrollToTarget('my-skills-area');
    });
});

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('header-showcase-button').addEventListener('click', function (e) {
        e.preventDefault();
        scrollToTarget('showcase-area');
    });

});

document.addEventListener("DOMContentLoaded", function () {
    const javaButton = document.getElementById('java-button');
    const webButton = document.getElementById('web-button');
    const othersButton = document.getElementById('others-button');

    javaButton.addEventListener('click', function () {
        showCategory('skills-java', javaButton);
    });

    webButton.addEventListener('click', function () {
        showCategory('skills-web', webButton);
    });

    othersButton.addEventListener('click', function () {
        showCategory('skills-others', othersButton);
    });
});

function showCategory(category, button) {
    const skillCards = document.querySelectorAll('.card');
    const categories = document.querySelectorAll('.category-button')
    skillCards.forEach(card => card.style.display = 'none');
    categories.forEach(categoryButton => categoryButton.style.backgroundColor = 'black')

    const categoryCards = document.querySelectorAll(`.${category} .card`);

    categoryCards.forEach(card => card.style.display = 'flex');
    button.style.backgroundColor = '#7d6981';

}
