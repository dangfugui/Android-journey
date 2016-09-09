<?php
/**
 * 对应数据库中的scenic表
 * @author yy
 *
 */
class Scenic {
	private $scenicid;
	private $scenicname;
	private $scenicdes;
	private $commentlist;
	private $star;
	private $imgpath;
	private $locale;
	
	public function setScenicid($scenicid){
		$this->scenicid=$scenicid;
	}
	public function getScenicid(){
		return $this->scenicid;
	}
	
	public function setScenicname($scenicname){
		$this->scenicname=$scenicname;
	}
	public function getScenicname(){
		return $this->scenicname;
	}
	
	public function setScenicdes($scenicdes){
		$this->scenicdes=$scenicdes;
	}
	public function getScenicdes(){
		return $this->scenicdes;
	}
	
	public function setCommentlist($commentlist){
		$this->commentlist=$commentlist;
	}
	public function getCommentlist(){
		return $this->commentlist;
	}
	
	public function setStar($star){
		$this->star=$star;
	}
	public function getStar(){
		return $this->star;
	}
	
	public function setImgpath($imgpath){
		$this->imgpath=$imgpath;
	}
	public function getImgpath(){
		return $this->imgpath;
	}
	
	public function setLocale($locale){
		$this->locale=$locale;
	}
	public function getLocale(){
		return $this->locale;
	}
}

?>