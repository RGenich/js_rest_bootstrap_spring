$(document).ready(function () {

    const headers = {
        'Content-Type': 'application/json'
    }
    let modalWin = document.getElementById('editModalForm');

    buildTable();

    $('#addButton').on('click', function () {
        addUser()
    });

    $('#usersTable').on('click', '.edit-btn', async function () {
        modalWin.style.display = 'block';

        $('#deleteButton').hide();
        $('#saveButton').show();

        $('#editModalForm').find('input, inpit:radio, input:checkbox').each(function () {
            $(this).prop('disabled', false);
        });

        let user = await getUserFormServerById(this.name);
        fillUserForm(user);

        buildTable();
    })

    $('#usersTable').on('click', '.delete-btn', async function () {

        modalWin.style.display = 'block';
        $('#saveButton').hide();
        $('#deleteButton').show();

        $('#editModalForm').find('input, inpit:radio, input:checkbox').each(function () {
            $(this).prop('disabled', true);

        });

        let user = await getUserFormServerById(this.name);

        fillUserForm(user);
        buildTable();
    })

    $('#saveButton').on('click', async function () {
        let user = {};
        let roles = [];
        let generalInfo = [];

        // массив отмеченных ролей
        $('#rolelist').find('input:checkbox').each(function () {
                if ($(this).is(':checked')) {
                    roles.push($(this).val())
                }
            }
        );
        user['roles'] = roles;

        //остальные данные
        generalInfo = $('#editModalForm').find('input:radio, input:text');
        for (let input of generalInfo) {
            user[String(input.name)] = String(input.value);
        }
        //передаем на сервер
        let responce = await fetch('/user/' + user.id, {method: 'PUT', headers, body: JSON.stringify(user)})

        if (responce.ok) {
            modalWin.style.display = 'none';
            await buildTable();
        }
    })

    $('#deleteButton').on('click', async function () {

        let responce = await fetch('/user/' + $('#id').val(), {method: 'DELETE', headers})

        if (responce.ok) {
            modalWin.style.display = 'none';
            await buildTable();
        }
    })

    $('.closeModal').on('click', function () {
        modalWin.style.display = 'none';
    })

    function fillUserForm(user) {

        $('#id').val(user.id);
        $('#login').val(user.login);
        $('#fullName').val(user.fullName);
        $('#age').val(user.age);
        if (user.sex == 'FEMALE') {
            $('#F').prop("checked", true);
        } else if (user.sex == 'MALE') {
            $('#M').prop("checked", true);
        }

        let userRoles = []
        for (let id of user.roles) {
            userRoles.push(id.roleName);
        }
        $('input:checkbox').each(function () {
            if (userRoles.indexOf($(this).val()) != -1) {
                $(this).prop('checked', true);
            }
        });
    }

    async function buildTable() {
        let responce = await fetch('/userlist', {method: 'GET', headers})

        if (responce.ok) {
            let userList = await responce.json();

            $('.usersintable').remove();
            for (userId in userList) {
                addUserToTable(userList[userId])
            }
        }
    }

    async function getUserFormServerById(id) {
        let responce = await fetch('/user/' + id, {method: 'GET', headers})
        let user;
        if (responce.ok) {
            user = await responce.json();
        }
        return user;
    }

    async function addUser() {
        let newUser = {
            fullName: $('#nFullName').val(),
            login: $('#nLogin').val(),
            password: $('#nPassword').val(),
            age: $('#nAge').val(),
            sex: $("#sex input[type='radio']:checked").val()
        }

        let result = await fetch('/adduser', {method: 'POST', headers, body: JSON.stringify(newUser)});

        if (result.ok) {
            let user = await result.json();
            addUserToTable(user);
        }
    }

    function addUserToTable(user) {
        $('#usersTable tr:last').after(
            `<tr class="usersintable">
                <td>${user.fullName}</td>
                <td>${user.login}</td>
                <td>${user.age}</td>
                <td>${user.sex}</td>
                <td><button type="button" class="btn btn-sm edit-btn" name="${user.id}">Edit</button></td>
                <td><button type="submit" value="delete" class="btn btn-danger btn-sm delete-btn" name="${user.id}">Delete</button></td>
            </tr>`
        )
    }
})
