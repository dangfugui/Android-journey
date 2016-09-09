<?php
/**
 * 对应数据库中的HelpArticle表
 * @author yy
 *
 */
class HelpArticle {
	private $helpid;
	private $helpheader;
	private $helpcontent;
	private $helptime;
	private $commentlist;
	
	public function setHelpid($helpid){
		$this->helpid=$helpid;
	}
	public function getHelpid(){
		return $this->helpid;
	}
	
	public function setHelpheader($helpheader){
		$this->helpheader=$helpheader;
	}
	public function getHelpheader(){
		return $this->helpheader;
	}
	
	public function setHelpcontent($helpcontent){
		$this->helpcontent=$helpcontent;
	}
	public function getHelpcontent(){
		return $this->helpcontent;
	}
	
	public function setHelptime($helptime){
		$this->helptime=$helptime;
	}
	public function getHelptime(){
		return $this->helptime;
	}
	
	public function setCommentlist($commentlist){
		$this->commentlist=$commentlist;
	}
	public function getCommentlist(){
		return $this->commentlist;
	}
}

?>