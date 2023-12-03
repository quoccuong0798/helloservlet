$(document).ready(function(){
	//logic code
	
	$(".btn-xoa").click(function(){
		var id = $(this).attr("id-role")
		var This=$(this)
		
		//goi duong dan va lay du lieu từ đường dẫn đó trả ra
		$.ajax({
			method: "DELETE",
			url: "http://localhost:8080/helloservlet/api/role?id="+id,
			//data: { name: "John", location: "Boston"}  // chỉ dành cho phương thức post, tham số truyền ngầm
		})
			.done(function(result) { 
				if(result.data==true){
				This.closest("tr").remove()
				}
				
				 
				console.log(result+ "Cường")
				  
			});     

	})
	
	
})