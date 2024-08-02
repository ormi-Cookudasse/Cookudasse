document.addEventListener("DOMContentLoaded", function () {
  var scrollToTopBtn = document.getElementById("scroll-to-top");
  var addRecipeBtn = document.getElementById("add-recipe");
  var addNoticeBtn = document.getElementById("add-notice");

  window.onscroll = function () {
    if (
        document.body.scrollTop > 20 ||
        document.documentElement.scrollTop > 20
    ) {
      scrollToTopBtn.style.display = "block";
    } else {
      scrollToTopBtn.style.display = "none";
    }
  };

  scrollToTopBtn.onclick = function () {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
  };

  addRecipeBtn.onclick = function (e) {
    window.location.href = "/write";
  };

  addNoticeBtn.onclick = function (e) {
    window.location.href = "/writeNotice";
  };
});