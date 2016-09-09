<?php
/**
 * 对应数据库的User表
 * @author yy
 *
 */
class User {
	private $userid;
	private $name;
	private $username;
	private $password;
	private $firendlist;
	private $grades;
	private $articlelist;
	private $phone;
	private $country;
	private $email;
	
	public function setUserid($userid){
		$this->userid=$userid;
	}
	public function getUserid(){
		return $this->userid;
	}
	public function setName($name){
		$this->name=$name;
	}
	public function getName(){
		return $this->name;
	}
	public function setUsername($username){
		$this->username=$username;
	}
	public function getUsername(){
		return $this->username;
	}
	public function setPassword($password){
		$this->password=$password;
	}
	public function getPassword(){
		return $this->password;
	}
	public function setFriendlist($friendlist){
		$this->firendlist=$friendlist;
	}
	public function getFriendlist(){
		return $this->firendlist;
	}
	public function setGrades($grades){
		$this->grades=$grades;
	}
	public function getGrades(){
		return $this->grades;
	}
	public function setArticlelist($articlelist){
		$this->articlelist=$articlelist;
	}
	public function getArticlelist(){
		return $this->articlelist;
	}
	public function setPhone($phone){
		$this->phone=$phone;
	}
	public function getPhone(){
		return $this->phone;
	}
	public function setCountry($country){
		$this->country=$country;
	}
	public function getCountry(){
		return $this->country;
	}
	public function setEmail($email){
		$this->email=$email;
	}
	public function getEmail(){
		return $this->email;
	}
	
}

?>