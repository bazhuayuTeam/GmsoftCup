// JScript 文件
/*
*author:Xiaowei Hsueh
*作者:薛晓伟
*email:Hsuehic@hotmail.com
*/

;jQuery.fn.extend(
{
    iframeAutoHeight:function ()
    {
        this.each(function ()
        {
            var w=this;
            var src=$(w).attr("src");
            $(this).load(function (){
                var contentHeight=this.contentDocument?this.contentDocument.body.scrollHeight:this.Document.body.offsetHeight;//w.contentDocument:netscape,w.document:ie
                $(this).css("height",contentHeight);
            });
            $(w).attr("src",src);
        }
        );
    }
}
);
