GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON RHAREA TO role ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON RHBENEFICIO TO role ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON RHcandidato TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhcandidatocarac TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhcandidatocurso TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhcandidatofunc TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhcandidatostatus TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhcaracteristica TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhcurso TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhdepto TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhempregado TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhempregadobenef TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhempregador TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhempregadosal TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhfuncao TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhnivelcurso TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhponto TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhturno TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhVaga TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhvagacandidato TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhvagacaracquali TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhvagacaracrest TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhvagacurso TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhvagastatus TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON sgestcivil TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhexpediente TO ROLE ADM;
GRANT DELETE, INSERT, SELECT, UPDATE, REFERENCES ON rhexpedmes TO ROLE ADM;
GRANT EXECUTE ON PROCEDURE RHLISTACANDVAGASP TO ROLE ADM;
GRANT EXECUTE ON PROCEDURE RHLISTAVAGACANDSP TO ROLE ADM;
GRANT EXECUTE ON PROCEDURE GERAEXPEDIENTESP TO ROLE ADM;
COMMIT WORK;
