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
            form.style.display = form.id === formId + '-form' ? 'block' : 'none';
        });

        tabs.forEach(tab => {
            tab.classList.toggle('active', tab.getAttribute('data-form') === formId);
        });
    }
});