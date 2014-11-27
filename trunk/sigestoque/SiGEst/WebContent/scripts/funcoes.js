 function formatarCpfCnpj(valor) {

       v = valor.value;
       // Remove tudo o que n�o � d�gito
       v = v.replace(/\D/g, "");

       if (14 > v.length) { // CPF

               // Coloca um ponto entre o terceiro e o quarto d�gitos
               v = v.replace(/(\d{3})(\d)/, "$1.$2");

               // Coloca um ponto entre o terceiro e o quarto d�gitos
               // de novo (para o segundo bloco de n�meros)
               v = v.replace(/(\d{3})(\d)/, "$1.$2");

               // Coloca um h�fen entre o terceiro e o quarto d�gitos
               v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2");

       } else { // CNPJ

               // Coloca ponto entre o segundo e o terceiro d�gitos
               v = v.replace(/^(\d{2})(\d)/, "$1.$2");

               // Coloca ponto entre o quinto e o sexto d�gitos
               v = v.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3");

               // Coloca uma barra entre o oitavo e o nono d�gitos
               v = v.replace(/\.(\d{3})(\d)/, ".$1/$2");

               // Coloca um h�fen depois do bloco de quatro d�gitos
               v = v.replace(/(\d{4})(\d)/, "$1-$2");

       }

       valor.value = v;

}
 
