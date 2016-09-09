<?php
/**
 * Created by PhpStorm.
 * User: duang
 * Date: 2015/6/29
 * Time: 1:08
 */
class  JsonHeper{
    public static function arrayRecursive(&$array, $function, $apply_to_keys_also = false)
    {
        foreach ($array as $key => $value) {
            if (is_array($value)) {
                JsonHeper::arrayRecursive($array[$key], $function, $apply_to_keys_also);
            } else {
                $array[$key] = $function($value);
            }
            if ($apply_to_keys_also && is_string($key)) {
                $new_key = $function($key);
                if ($new_key != $key) {
                    $array[$new_key] = $array[$key];
                    unset($array[$key]);
                }
            }
        }
    }
    /**************************************************************
     *
     *    将数组转换为JSON字符串（兼容中文）
     *    @param    array    $array        要转换的数组
     *    @return string        转换得到的json字符串
     *    @access public
     *
     *************************************************************/
   public static function JSON($array) {
        JsonHeper::arrayRecursive($array, 'urlencode', true);
        $json = json_encode($array);
        return urldecode($json);
    }

}
?>