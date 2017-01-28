<?php 

	require_once('db.php');

	$mahasiswa_id = $_REQUEST['mahasiswa_id'];
	$query = mysqli_query($conn, "SELECT mhs.*, prd.nama AS prodi_nama FROM mahasiswa AS mhs LEFT JOIN prodi AS prd ON mhs.prodi_id = prd.prodi_id WHERE mahasiswa_id='$mahasiswa_id'");
	
	$result = array();
	while($row = mysqli_fetch_array($query)){
		$tgl_lahir = date("d/m/Y", strtotime($row['tgl_lahir']));
		$d = date("d", strtotime($row['tgl_lahir']));
		$m = date("m", strtotime($row['tgl_lahir']));
		$y = date("Y", strtotime($row['tgl_lahir']));
		array_push($result,array(
			'mahasiswa_id'	=> $row['mahasiswa_id'],
			'nim'			=> $row['nim'],
			'nama'			=> $row['nama'],
			'tgl_lahir1'	=> $tgl_lahir,
			'd'				=> $d,
			'm'				=> $m,
			'y'				=> $y,
			'tgl_lahir2'	=> $row['tgl_lahir'],
			'prodi_id'		=> $row['prodi_id'],
			'prodi_nama'	=> $row['prodi_nama'],
			'alamat'		=> $row['alamat'],
		));
	}
	
	echo json_encode(array('result'=>$result));
	mysqli_close($conn);

 ?>