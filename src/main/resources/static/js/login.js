// document.addEventListener('DOMContentLoaded', function() {
//     const tabs = document.querySelectorAll('.tab');
//     const forms = document.querySelectorAll('form');
//
//     tabs.forEach(tab => {
//         tab.addEventListener('click', function() {
//             const formId = this.getAttribute('data-form');
//             showForm(formId);
//         });
//     });
//
//     function showForm(formId) {
//         forms.forEach(form => {
//             form.style.display = form.id === formId + '-form' ? 'block' : 'none';
//         });
//
//         tabs.forEach(tab => {
//             tab.classList.toggle('active', tab.getAttribute('data-form') === formId);
//         });
//     }
// });

document.addEventListener('DOMContentLoaded', function() {
    const tabs = document.querySelectorAll('.tab');
    const forms = document.querySelectorAll('form');

    tabs.forEach(tab => {
        tab.addEventListener('click', function() {
            const formId = this.getAttribute('data-form');
            showForm(formId);
        });
    });

    function showForm(formId) {
        forms.forEach(form => {
            if (form.id === formId + '-form') {
                form.style.display = 'block';
                form.disabled = false;
            } else {
                form.style.display = 'none';
                form.disabled = true;
            }
        });

        tabs.forEach(tab => {
            tab.classList.toggle('active', tab.getAttribute('data-form') === formId);
        });

        // 폼 전환 후 첫 번째 입력 필드에 포커스
        const activeForm = document.getElementById(formId + '-form');
        const firstInput = activeForm.querySelector('input');
        if (firstInput) firstInput.focus();
    }

    // 페이지 로드 시 기본적으로 signin 폼을 보이게 설정
    showForm('signin');
});

