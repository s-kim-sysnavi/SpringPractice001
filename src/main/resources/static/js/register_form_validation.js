//submit用ステータス確認関数
const fieldStates = {
	username: false,
	password: false,
	name: false,
	gender: false,
	address: false,
	joinyear: false
};

const usernameInput = document.getElementById('username');
const usernameError = document.getElementById('usernameError');
const passwordInput = document.getElementById('password');
const passwordError = document.getElementById('passwordError');
const nameInput = document.getElementById('name');
const nameError = document.getElementById('nameError');
const seiInput = document.getElementById('sei');
const addressInput = document.getElementById('address');
const nenInput = document.getElementById('nen');
const submitButton = document.querySelector('button[type="submit"]');

// email形式検証
function validateEmail(value) {
	const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	return emailPattern.test(value.trim());
}

function validateUsername() {
	const value = usernameInput.value;

	if (value.trim() === '') {
		usernameError.textContent = 'メールアドレスを入力してください。';
		fieldStates.username = false;
	}
	else if (!validateEmail(value)) {
		usernameError.textContent = '正しい形式のメールアドレスを入力してください。';
		fieldStates.username = false;
	}
	else {
		usernameError.textContent = '';
		fieldStates.username = true;
	}
	toggleSubmitButton();
}

function validatePassword() {
	const value = passwordInput.value;

	if (value.trim() === '') {
		passwordError.textContent = 'パスワードを入力してください';
		fieldStates.password = false;
	}
	else if (value.trim().length < 4) {
		passwordError.textContent = 'パスワードは4文字以上で入力してください';
		fieldStates.password = false;
	}
	else {
		passwordError.textContent = '';
		fieldStates.password = true;
	}
	toggleSubmitButton();
}

function validateName() {
	const value = nameInput.value;

	if (value.trim() === '') {
		nameError.textContent = '名前を入力してください';
		fieldStates.name = false;
	}
	else {
		nameError.textContent = '';
		fieldStates.name = true;
	}
	toggleSubmitButton();
}

function validateGender() {
	const value = seiInput.value;

	if (value.trim() === '') {
		seiError.textContent = '性別を選択してください';
		fieldStates.gender = false;
	}
	else {
		seiError.textContent = '';
		fieldStates.gender = true;
	}
	toggleSubmitButton();
}

function validateAddress() {
	const value = addressInput.value;

	if (value.trim() === '') {
		addressError.textContent = '住所を入力してください';
		fieldStates.address = false;
	}
	else {
		addressError.textContent = '';
		fieldStates.address = true;
	}
	toggleSubmitButton();
}

function validateJoinyear() {
	const value = nenInput.value;

	if (value.trim() === '') {
		nenError.textContent = '入社年度を選択してください';
		fieldStates.joinyear = false;
	}
	else {
		nenError.textContent = '';
		fieldStates.joinyear = true;
	}
	toggleSubmitButton();
}

function toggleSubmitButton() {
    const allValid = Object.values(fieldStates).every(state => state === true);
    submitButton.disabled = !allValid; 
}

document.addEventListener('DOMContentLoaded', () => {
	validateUsername();
	validatePassword();
	validateName();
	validateGender();
	validateAddress();
	validateJoinyear();
});

usernameInput.addEventListener('input', validateUsername);
passwordInput.addEventListener('input', validatePassword);
nameInput.addEventListener('input', validateName);
seiInput.addEventListener('input', validateGender);
addressInput.addEventListener('input', validateAddress);
nenInput.addEventListener('input', validateJoinyear);
