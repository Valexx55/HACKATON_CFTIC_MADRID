import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;



void main() {
  runApp(MaterialApp(
    home: ComunidadMadridData(),
  ));
}
class ComunidadMadridData extends StatefulWidget {
  @override
  ComunidadMadridState createState() => ComunidadMadridState();
}
class ComunidadMadridState extends State<ComunidadMadridData> {
  final String url = "https://datos.madrid.es/egob/catalogo/200342-0-centros-dia.json";
  late List data;

  Future<String> getSWData() async {
    var res =
    await http.get(Uri.parse(url), headers: {"Accept": "application/json"});

    setState(() {
      var resBody = json.decode(res.body);
      data = resBody["@graph"];
    });

    return "Success!";
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("CENTROS DE DÍA DE LA COMUNIDAD DE MADRID"),
          backgroundColor: Colors.deepOrangeAccent,
        ),
        body: ListView.builder(
            itemCount: data == null ? 0 : data.length,
            itemBuilder: (BuildContext context, int index) {
              return Container(
                  child: Center(
                      child: Column(
                          crossAxisAlignment: CrossAxisAlignment.stretch,
                          children: <Widget>[
                            Card(
                              child: Container(
                                  padding: EdgeInsets.all(15.0),
                                  child: Row(
                                    children: <Widget>[
                                          Text(data[index]["title"],
                                          style: TextStyle(
                                              fontSize: 12.0,
                                              color: Colors.blueAccent)),
                                    ],
                                  )),
                            ),
                          ]
                      )
                  )
              );
            }));
  }

  @override
  void initState() {
    super.initState();
    this.getSWData();
  }
}

