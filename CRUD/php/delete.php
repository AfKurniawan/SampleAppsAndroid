<?php
	require_once('db.php');

	$mahasiswa_id	= $_REQUEST['mahasiswa_id'];

	$query = mysqli_query($conn, "DELETE FROM mahasiswa WHERE mahasiswa_id='$mahasiswa_id'");
	if($query) {
	    echo '{"result":"berhasil"}';
	} else {
	    echo '{"result":"gagal"}';
	}
?>