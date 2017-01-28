<?php
	
	require_once('db.php');

	$mahasiswa_id	= $_REQUEST['mahasiswa_id'];
	$nim			= $_REQUEST['nim'];   
	$nama			= $_REQUEST['nama'];
	$tgl_lahir		= $_REQUEST['tgl_lahir'];
	$prodi_id		= $_REQUEST['prodi_id'];
	$alamat			= $_REQUEST['alamat'];

	$query = mysqli_query($conn, "UPDATE mahasiswa SET nim = '$nim', nama='$nama', tgl_lahir='$tgl_lahir', prodi_id='$prodi_id', alamat='$alamat' WHERE mahasiswa_id='$mahasiswa_id'");

	if($query) {
	    echo '{"result":"berhasil"}';
	} else {
	    echo '{"result":"gagal"}';
	}
?>