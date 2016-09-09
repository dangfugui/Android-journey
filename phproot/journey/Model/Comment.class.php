<?php
/**
 * 对应数据库中的Comment表
 * @author yy
 *
 */
class Comment {
	private $commentid;
	private $userid;
	private $comments;
	private $commenttime;
	
	public function setCommentid($commentid){
		$this->commentid=$commentid;
	}
	public function getCommentid(){
		return $this->commentid;
	}
	
	public function setUserid($userid){
		$this->userid=$userid;
	}
	public function getUserid(){
		return $this->userid;
	}
	
	public function setComments($comments){
		$this->comments=$comments;
	}
	public function getComments(){
		return $this->comments;
	}
	
	public function setCommenttime($commenttime){
		$this->commenttime=$commenttime;
	}
	public function getCommenttime(){
		return $this->commenttime;
	}
}

?>