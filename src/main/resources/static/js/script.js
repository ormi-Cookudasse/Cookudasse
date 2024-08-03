// 글 작성 페이지 기능
if (document.getElementById("post-form")) {
  document.getElementById("post-form").addEventListener("submit", function (e) {
    e.preventDefault();
    // 여기에 서버로 데이터를 전송하는 코드를 추가합니다.
    alert("글이 저장되었습니다.");
  });
}

// 글 조회 페이지 기능
if (document.querySelector(".post-view")) {
  // 서버에서 데이터를 가져오는 대신 임시 데이터를 사용합니다.
  const postData = {
    title: postData.title,
    category: postData.category,
    ingredients: postData.ingredients,
    recipe: postData.recipe,
  };

  document.getElementById("post-title").textContent = postData.title;
  document.getElementById("post-category").textContent = postData.category;
  document.getElementById("post-ingredients").textContent =
    postData.ingredients;
  document.getElementById("post-recipe").innerHTML = postData.recipe;

  // 반응 버튼 기능
  document.querySelectorAll(".reaction-btn").forEach((btn) => {
    btn.addEventListener("click", function () {
      alert("반응이 저장되었습니다.");
    });
  });
}
