function submitForm() {
    const users = document.querySelectorAll('table tbody tr');
    const updatedUsers = [];

    users.forEach(user => {
        const userId = user.querySelector('td:first-child').textContent;
        const roleSelect = user.querySelector('select[name="role"]');
        const selectedRole = roleSelect.value;

        updatedUsers.push({
            userId: userId,
            role: selectedRole
        });
    });

    fetch('/api/admin/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedUsers)
    })
        .then(response => {
            if (response.ok) {
                alert('사용자 권한이 업데이트되었습니다.');
                window.location.reload();
            } else {
                alert('업데이트 중 오류가 발생했습니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('업데이트 중 오류가 발생했습니다.');
        });
}