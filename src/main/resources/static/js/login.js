document.addEventListener('DOMContentLoaded', function () {

    const tabs = document.querySelectorAll('.tab');
    const forms = document.querySelectorAll('form');

    // 탭 전환 로직
    tabs.forEach(tab => {
        tab.addEventListener('click', function () {
            const formId = this.getAttribute('data-form');
            showForm(formId);
        });
    });

    function showForm(formId) {
        forms.forEach(form => {
            form.style.display = form.id === formId + '-form' ? 'block' : 'none';
        });

        tabs.forEach(tab => {
            tab.classList.toggle('active', tab.getAttribute('data-form') === formId);
        });
    }

    // 폼 전환 후 첫 번째 입력 필드에 포커스
    const activeForm = document.getElementById(formId + '-form');
    const firstInput = activeForm.querySelector('input');
    if (firstInput) firstInput.focus();


    // 페이지 로드 시 기본적으로 signin 폼을 보이게 설정
    showForm('signin');

// 로그인 폼 제출 처리
    const loginForm = document.getElementById('signin-form');
    if (loginForm) {
        loginForm.addEventListener('submit', function (event) {
            event.preventDefault();
            // 여기에 폼 유효성 검사 로직을 추가할 수 있습니다.
            this.submit();
        });
    }
});