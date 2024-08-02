document.addEventListener("DOMContentLoaded", function () {
  var scrollToTopBtn = document.getElementById("scroll-to-top");
  var addRecipeBtn = document.getElementById("add-recipe");

  // 레시피 그리드에 아이템 추가
  var recipeGrid = document.getElementById("recipe_grid");
  var recipes = [
    { image: "img/비빔밥.jpg", title: "비빔밥" },
    { image: "img/냉면.jpg", title: "국수" },
    { image: "img/비빔면.jpg", title: "비비면" },
    { image: "img/닭볶음탕.jpg", title: "닭볶음탕" },
    { image: "img/김치찌개.jpg", title: "김치찌개" },
    { image: "img/김치볶음밥.jpg", title: "김치볶음밥" },
    { image: "img/냉면.jpg", title: "냉면" },
    { image: "img/계란볶음밥.jpg", title: "계란볶음밥" },
    { image: "img/샐러드.jpg", title: "샐러드" },
  ];

  recipes.forEach(function (recipe) {
    var recipeItem = document.createElement("div");
    recipeItem.className = "recipe-item";
    recipeItem.innerHTML = `
            <img src="${recipe.image}" alt="${recipe.title}">
            <h3>${recipe.title}</h3>
        `;
    recipeGrid.appendChild(recipeItem);
  });

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
    window.location.href = "newRecipe.html";
  };
});
