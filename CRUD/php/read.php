<?php 

	require_once('db.php');
	$query = mysqli_query($conn, "SELECT mhs.*, prd.nama AS prodi_nama FROM mahasiswa AS mhs LEFT JOIN prodi AS prd ON mhs.prodi_id = prd.prodi_id ORDER BY mhs.mahasiswa_id DESC");
	
	$result = array();
	while($row = mysqli_fetch_array($query)){
		$tgl_lahir = date("d/m/Y", strtotime($row['tgl_lahir']));
		array_push($result,array(
			'mahasiswa_id'	=> $row['mahasiswa_id'],
			'nim'			=> $row['nim'],
			'nama'			=> $row['nama'],
			'tgl_lahir'		=> $tgl_lahir,
			'prodi_id'		=> $row['prodi_id'],
			'prodi_nama'	=> $row['prodi_nama'],
			'alamat'		=> $row['alamat'],
		));
	}
	
	echo json_encode(array('result'=>$result));
	mysqli_close($conn);

 ?>