document.addEventListener("DOMContentLoaded", () => {
	const dropZone = document.getElementById("drag-and-drop");
	const fileInput = document.getElementById("file");
	const form = document.getElementById("upload-form");
	const fileInfo = document.getElementById("file-info");
	const preview = document.getElementById("preview");
	const dropZoneText = dropZone.querySelector("p");

	// ドラッグアンドドロップ
	dropZone.addEventListener("dragover", (event) => {
		event.preventDefault();
		dropZone.classList.add("dragover");
	});

	// アップロード領域外での動き
	dropZone.addEventListener("dragleave", () => {
		dropZone.classList.remove("dragover");
	});

	// ドロップイベント
	dropZone.addEventListener("drop", (event) => {
		event.preventDefault();
		dropZone.classList.remove("dragover");

		// ファイル読み取り
		const files = event.dataTransfer.files;
		if (files.length > 0) {
			fileInput.files = files;
			displayFileName(files[0]);
			displayImages(files);
			dropZone.classList.add("dragged");
			updateDropZoneText("ファイル選択済みです。");
		}
	});

	// 選択時の表示
	fileInput.addEventListener("change", (event) => {
		const files = event.target.files;
		if (files.length > 0) {
			displayFileName(files[0]);
			displayImages(files);
			updateDropZoneText("ファイル選択済みです。");
			dropZone.classList.add("dragged");
		}
	});

	// ファイル名表記
	function displayFileName(file) {
		fileInfo.textContent = `選択したファイル: ${file.name}`;
		fileInfo.style.color = "blue";
		fileInfo.style.fontSize = "14px";
	}




	// ファイルのプレービュー
	function displayImages(files) {
		preview.innerHTML = "";
		Array.from(files).forEach((file) => {
			if (file.type.startsWith("image/")) {
				const reader = new FileReader();
				reader.onload = (event) => {
					const img = document.createElement("img");
					img.src = event.target.result;
					img.alt = file.name;
					img.classList.add("preview-image");
					preview.appendChild(img);
				};
				reader.readAsDataURL(file);
			} else {
				alert("画像ファイルのみ、アップロード可能です。");
			}
		});
	}

	//ファイル選択後、表示文変更
	function updateDropZoneText(text) {
		dropZoneText.textContent = `${text}`;
	}

	// バリデーション
	form.addEventListener("submit", (event) => {
		if (fileInput.files.length === 0) {
			event.preventDefault();
			alert("ファイルを選ぶかドラッグアンドドロップしてください。");
		}
	});
});
