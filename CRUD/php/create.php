<?php
	
	require_once('db.php');

	$nim		= $_REQUEST['nim'];
	$nama		= $_REQUEST['nama'];
	$tgl_lahir	= $_REQUEST['tgl_lahir'];   
	$prodi_id	= $_REQUEST['prodi_id'];
	$alamat		= $_REQUEST['alamat'];
	
	$query 		= mysqli_query($conn, "INSERT INTO mahasiswa (nim, nama, tgl_lahir, prodi_id, alamat) VALUES ('$nim', '$nama', '$tgl_lahir', '$prodi_id', '$alamat') ");

	if($query) {
	    echo '{"result":"berhasil"}';
	} else {
	    echo '{"result":"gagal"}';
	}
?>