var treePalette1 = isc.TreePalette.create({
    width: "20%",
    showHeader:false, 
    fields: [{
        name: "title",
        title: "菜单",
        width: "100%",
        align: "center"
    }]
})

isc.VLayout.create({
    width: "100%",
    height: "100%",
    members: [
        isc.Label.create({
        	height: "75",
            contents: "Navigation",
            align: "center",
            overflow: "hidden",
            border: "1px solid blue"
        }),
        
        isc.HLayout.create({
            width: "100%",
            members: [
                treePalette1, //导航树
                
				isc.TabSet.create({
				    ID: "tabSet",
				    tabBarPosition: "top",
				    tabBarAlign: "left",
				    width: '100%',
				    tabs: [
				        {title: "Blue", iconSize:16, pane: isc.Img.create({autoDraw: false, width: 48, height: 48, src: "pieces/48/pawn_green.png"})},
				        {title: "Green",iconSize:16, pane: isc.Img.create({autoDraw: false, width: 48, height: 48, src: "pieces/48/pawn_green.png"})}
				    ]
				})
            ]
        }),
        
        isc.Label.create({
         	height: "25",
         	width: "100%",
            contents: "Listing",
            align: "center",
            overflow: "hidden",
            border: "1px solid blue"
        })
    ]
})

$(function(){
	
	treePalette1.setData(
		isc.Tree.create({
	        ID: "componentTree",
	        root: {
	            children: [{
	                title: "Canvas",
	                canDrag: false,
	                isFolder: true,
	                children: [{
	                    title: "Blue Canvas",
	                    type: "Canvas"
	                },{
	                    title: "Red Canvas", 
	                    type: "Canvas"
	                }]
	            },{
	                title: "Images",
	                canDrag: false,
	                isFolder: true,
	                children: [{
	                    title: "Alligator",
	                    type: "Img"
	                },{
	                    title: "Anteater",
	                    type: "Img"
	                }]
	            }]
	        }
	    })
	)
	
	
	
	
	
})
		
		
		