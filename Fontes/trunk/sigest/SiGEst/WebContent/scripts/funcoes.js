 function formatarCpfCnpj(valor) {

       v = valor.value;
       // Remove tudo o que não é dígito
       v = v.replace(/\D/g, "");

       if (14 > v.length) { // CPF

               // Coloca um ponto entre o terceiro e o quarto dígitos
               v = v.replace(/(\d{3})(\d)/, "$1.$2");

               // Coloca um ponto entre o terceiro e o quarto dígitos
               // de novo (para o segundo bloco de números)
               v = v.replace(/(\d{3})(\d)/, "$1.$2");

               // Coloca um hífen entre o terceiro e o quarto dígitos
               v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2");

       } else { // CNPJ

               // Coloca ponto entre o segundo e o terceiro dígitos
               v = v.replace(/^(\d{2})(\d)/, "$1.$2");

               // Coloca ponto entre o quinto e o sexto dígitos
               v = v.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3");

               // Coloca uma barra entre o oitavo e o nono dígitos
               v = v.replace(/\.(\d{3})(\d)/, ".$1/$2");

               // Coloca um hífen depois do bloco de quatro dígitos
               v = v.replace(/(\d{4})(\d)/, "$1-$2");

       }

       valor.value = v;

}
 
google.load("visualization", "1.1", {packages:["bar"]});
google.setOnLoadCallback(drawChart);
function drawChart() {
var data = google.visualization.arrayToDataTable([
  ['Ano', 'vendas', 'despesas', 'lucro'],
  ['2015', 1170, 460, 250],
  ['2016', 660, 1120, 300],
  ['2017', 1030, 540, 350],
  ['2018', 1000, 400, 200],
  ['2019', 1150, 460, 250],
  ['2020', 1000, 400, 200]
]);

var options = {
  chart: {
    title: 'Desempenho da empresa:',
    subtitle: 'vendas, despesas e lucro: 2015-2017',
  }
};

var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

chart.draw(data, options);
}
 
 jQuery(function($){     
     $(".date").mask("99/99/9999");     
     $(".phone").mask("(999) 999-9999");     
     $(".tin").mask("99-9999999");     
     $(".ssn").mask("999-99-9999");     
 });    
