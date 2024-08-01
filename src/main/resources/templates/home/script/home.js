document.addEventListener('DOMContentLoaded', function() {
    var scrollToTopBtn = document.getElementById("scroll-to-top");
    var addRecipeBtn = document.getElementById("add-recipe");

    window.onscroll = function() {
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
            scrollToTopBtn.style.display = "block";
        } else {
            scrollToTopBtn.style.display = "none";
        }
    };

    scrollToTopBtn.onclick = function() {
        document.body.scrollTop = 0; // For Safari
        document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
    };

    // 레시피 추가 버튼 클릭 시 newRecipe.html로 이동
    addRecipeBtn.onclick = function(e) {
        window.location.href = 'newRecipe.html';
    };
});