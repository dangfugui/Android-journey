<?php
/**
 * 对应数据库中的Article表
 * @author yy
 *
 */
class Article {
	 private $articleid;
	 private $contents;
	 private $header;
	 private $sendtime;
	 private $commentlist;
	 private $articlemode;
	 private $starcount;
	 
	 public function setArticleid($articleid){
	 	$this->articleid=$articleid;
	 }
	 public function getArticleid(){
	 	return $this->articleid;
	 }
	 
	 public function setContens($contents){
	 	$this->contents=$contents;
	 }
	 public function getContents(){
	 	return $this->contents;
	 }
	 
	 public function setHeader($header){
	 	$this->header=$header;
	 }
	 public function getHeader(){
	 	return $this->header;
	 }
	 
	 public function setSendtime($sendtime){
	 	$this->sendtime=$sendtime;
	 }
	 public function getSendtime(){
	 	return $this->sendtime;
	 }
	 
	 public function setCommentlist($commentlist){
	 	$this->commentlist=$commentlist;
	 }
	 public function getCommentlist(){
	 	return $this->commentlist;
	 }
	 
	 public function setArticlemode($articlemode){
	 	$this->articlemode=$articlemode;
	 }
	 public function getArticlemode(){
	 	return $this->articlemode;
	 }
	 
	 public function setStarcount($starcount){
	 	$this->starcount=$starcount;
	 }
	 public function getStarcount(){
	 	return $this->starcount;
	 }
}

?>