<?php

// array untuk json
$response = array();

if (isset($_FILES['image'])) {
	if(isset($_POST['directory'])){
		$directory = $_POST['directory'];
		$full_directory_path = '../' . $directory;
		
		//Pengecekan folder, sudah tersedia atau belum
		if(!is_dir($full_directory_path)){
		
			//Pembuatan folder baru
			mkdir($full_directory_path, 0777, true);
		}
		//Menentukan tempat file akan disimpan
		$target_path = $full_directory_path . '/' . basename($_FILES['image']['name']);
		if (!move_uploaded_file($_FILES['image']['tmp_name'], $target_path)) {
		
			//File gagal dipindahkan ke server, biasanya karena folder yang dituju tidak tersedia
			$response['kode'] = 1;
			$response['pesan'] = "File tidak dapat dipindahkan ke server";
			echo json_encode($response);
		}else{
			// File berhasil diupload
			$response['kode'] = 2;
			$response['pesan'] = "File berhasil diupload";
			echo json_encode($response);
		}
	}else{
		
	}
} else {
	
	//Jika file tidak terkirim dari android
	$response['kode'] = 0;
	$response['pesan'] = 'File tidak terkirim ke server';
	echo json_encode($response);
}
?>