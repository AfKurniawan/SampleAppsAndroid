<?php 

	require_once('db.php');
	$query = mysqli_query($conn, "SELECT * FROM prodi");
	
	$result = array();
	while($row = mysqli_fetch_array($query)){
		array_push($result,array(
			'prodi_id'	=> $row['prodi_id'],
			'nama'		=> $row['nama']
		));
	}
	
	echo json_encode(array('result'=>$result));
	mysqli_close($conn);

 ?>