
function bindtopSearch(id){
	$("#"+id+' .slideDown').on('click', function(){
		$(this).find('i').toggleClass(function(){
			if ($(this).hasClass('icon-chevron-up')) {
				$(this).removeClass('icon-chevron-up').siblings('span').text('高级搜索');
				return '';
			} else {
				$(this).siblings('span').text('收起');
				return 'icon-chevron-up';
			}
		});
		$(this).parent().siblings('.row').slideToggle();
	});
}
