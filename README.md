# pdf-graph-digitalizor
A digitalizor for vectorial graphs included in a PDF file.

## Resume
This mini-application is provided to extract the points of a graph present in a PDF file.

![alt tag](https://github.com/FredericBoisguerin/pdf-graph-ditigalizor/blob/master/screenshot.png)

### Note: PDF reframing
In this first version, the application only manages PDF with one graph, as shown in the directory samples.

If you need to "reframe" your graphs, you can use **[gpdfx](https://github.com/lehner/gpdfx)** (UNIX environment required).

_Note 1: don't forget to add the executable **clipdfx** to your PATH variable environment._

_Note 2: You may need to install some dependencies before launching gpdfx :_
> `sudo apt-get install python-poppler python texlive-extra-utils texlive`

## Getting started

1. Clone this project.
2. Download some samples [here](https://github.com/FredericBoisguerin/pdf-graph-ditigalizor/tree/master/samples).
3. Launch the main method of the class
`com.fredericboisguerin.pdf.ui.UIController`.
4. Select a PDF file.
5. Enter the axes required information.
6. Select the series you want to be added to the output file.
7. Click on Go!
8. After processing, your spreadsheet editor will automatically open with the output file (the first sheet of the file is empty, the second contains the results).

## Excel template edition
You can edit the Excel template in the following resources directory
> `com/fredericboisguerin/graph/report`