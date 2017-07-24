# Datasheet graph digitalizor
A digitalizor for vectorial graphs included in a Datasheet PDF file.

![alt tag](https://github.com/freddyb2/datasheet-graph-digitalizor/blob/master/screenshots/screenshot-extract.png)

### Note: PDF crop

From now, you don't have to use `gpdfx` anymore, since we added a crop feature inside the application!

![alt tag](https://github.com/freddyb2/datasheet-graph-digitalizor/blob/master/screenshots/screenshot-crop.png)


## Getting started

1. Clone the project
2. `mvn clean install` and deploy the war in [tomcat](http://tomcat.apache.org/)
4. Create a datasheet
5. Create a graph for this datasheet (you will find some some samples [here](https://github.com/freddyb2/datasheet-graph-digitalizor/tree/master/samples))
6. Click "Extract data"
7. Fill the fields and click on Export
8. After processing, your spreadsheet is downloaded


## Excel template edition
You can edit the Excel template in the following resources directory: `com/fredericboisguerin/graph/report`